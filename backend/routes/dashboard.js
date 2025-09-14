// backend/routes/dashboard.js
const express = require("express");
const router = express.Router();

router.get("/", (req, res) => {
  res.json({
    totalRevenue: 50000,
    totalSales: 120,
    totalCustomers: 80,
    deliveriesInProgress: 15,
  });
});

module.exports = router;
