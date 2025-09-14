// backend/routes/auth.js
import express from "express";
import bcrypt from "bcryptjs";
import jwt from "jsonwebtoken";
import cookieParser from "cookie-parser"; // not used here but server uses it
import { v4 as uuidv4 } from "uuid";
import User from "../models/User.js";

const router = express.Router();

// helper to sign access token
const signAccessToken = (userId) => {
  return jwt.sign({ id: userId }, process.env.JWT_SECRET, { expiresIn: "15m" }); // short lived
};

// helper to create refresh token (random uuid stored in DB)
const createRefreshToken = () => uuidv4();

// REGISTER
router.post("/register", async (req, res) => {
  try {
    const { username, password } = req.body;
    const userExists = await User.findOne({ username });
    if (userExists) return res.status(400).json({ message: "User already exists" });

    const hashedPassword = await bcrypt.hash(password, 10);
    const newUser = new User({ username, password: hashedPassword });
    await newUser.save();

    res.json({ message: "User registered successfully" });
  } catch (err) {
    res.status(500).json({ message: "Server error" });
  }
});

// LOGIN
router.post("/login", async (req, res) => {
  try {
    const { username, password } = req.body;
    const user = await User.findOne({ username });
    if (!user) return res.status(400).json({ message: "Invalid credentials" });

    const isMatch = await bcrypt.compare(password, user.password);
    if (!isMatch) return res.status(400).json({ message: "Invalid credentials" });

    // create tokens
    const accessToken = signAccessToken(user._id);
    const refreshToken = createRefreshToken();

    // store refresh token in DB
    user.refreshToken = refreshToken;
    await user.save();

    // send refresh token as httpOnly cookie; send access token in body
    res.cookie("refreshToken", refreshToken, {
      httpOnly: true,
      secure: process.env.NODE_ENV === "production",
      sameSite: "lax",
      path: "/api/auth/refresh", // cookie only sent to refresh endpoint (optional)
      maxAge: 7 * 24 * 60 * 60 * 1000, // 7 days
    });

    return res.json({ token: accessToken });
  } catch (err) {
    console.error(err);
    res.status(500).json({ message: "Server error" });
  }
});

// REFRESH - issues a new access token when refresh cookie valid
router.post("/refresh", async (req, res) => {
  try {
    const refreshToken = req.cookies?.refreshToken;
    if (!refreshToken) return res.status(401).json({ message: "No refresh token" });

    // find user with that refresh token
    const user = await User.findOne({ refreshToken });
    if (!user) return res.status(401).json({ message: "Invalid refresh token" });

    // optionally check token expiry if you implement expiry; here refreshToken is UUID
    const newAccessToken = signAccessToken(user._id);
    return res.json({ token: newAccessToken });
  } catch (err) {
    console.error(err);
    res.status(500).json({ message: "Server error" });
  }
});

// LOGOUT - clears refresh token in DB and clears cookie
router.post("/logout", async (req, res) => {
  try {
    const refreshToken = req.cookies?.refreshToken;
    if (refreshToken) {
      // remove refresh token from DB
      await User.findOneAndUpdate({ refreshToken }, { $unset: { refreshToken: 1 } });
    }
    // clear cookie
    res.clearCookie("refreshToken", { path: "/api/auth/refresh" });
    return res.json({ message: "Logged out" });
  } catch (err) {
    console.error(err);
    res.status(500).json({ message: "Server error" });
  }
});

export default router;
