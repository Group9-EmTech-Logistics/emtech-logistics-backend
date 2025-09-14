import express from "express";
import jwt from "jsonwebtoken";

const router = express.Router();

// Middleware to check token
const authMiddleware = (req, res, next) => {
  const authHeader = req.headers.authorization;
  if (!authHeader) return res.status(401).json({ msg: "No token provided" });

  const token = authHeader.split(" ")[1];
  try {
    const decoded = jwt.verify(token, "yourSecretKey");
    req.user = decoded;
    next();
  } catch (err) {
    return res.status(401).json({ msg: "Invalid token" });
  }
};

// Example protected routes
router.get("/inventory", authMiddleware, (req, res) => {
  res.json([
    { _id: 1, name: "Laptop", quantity: 10 },
    { _id: 2, name: "Mouse", quantity: 25 },
  ]);
});

router.get("/sales", authMiddleware, (req, res) => {
  res.json([
    { _id: 1, product: "Laptop", amount: 1000 },
    { _id: 2, product: "Mouse", amount: 20 },
  ]);
});

export default router;