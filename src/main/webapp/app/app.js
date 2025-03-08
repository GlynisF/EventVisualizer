const express = require('express');
const { createProxyMiddleware } = require('http-proxy-middleware');

const app = express();

// Proxy only requests starting with /eventvisualizer to localhost:3000
app.use('/eventvisualizer', createProxyMiddleware({
  target: 'http://localhost:3000',  // Forward to localhost:3000
  changeOrigin: true,               // Adjusts the Host header to match target
  logLevel: 'debug'                 // Optional: logs proxy activities for debugging
}));

// Start the server
app.listen(8080, () => {
  console.log('Proxy server running on http://localhost:8080, forwarding /eventvisualizer to http://localhost:3000');
});
