const express = require('express');
const proxy = require('express-http-proxy');
const cors = require('cors');

const app = express();

// ✅ Allow CORS for your Angular dev server
app.use(cors({
  origin: 'http://localhost:4200',
  credentials: true
}));

// ✅ Proxy /api to backend, rewriting to /eventvisualizer/app
app.use('/api', proxy('http://localhost:8080', {
  proxyReqPathResolver: req => `/eventvisualizer/app${req.url}`
}));

const PORT = 3001;
app.listen(PORT, () => {
  console.log(`🚀 Proxy server running at http://localhost:${PORT}`);
});
