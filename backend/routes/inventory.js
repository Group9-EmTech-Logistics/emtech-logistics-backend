// backend/routes/inventory.js
import express from "express";
import authMiddleware from "../middleware/authMiddleware.js";

const router = express.Router();

router.get("/", authMiddleware, (req, res) => {
  res.json([
    { id: 1, item: "Laptop", quantity: 5 },
    { id: 2, item: "Mouse", quantity: 20 },
  ]);
});

export default router;
