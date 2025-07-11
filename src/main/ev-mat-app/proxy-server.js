const express = require('express');
const proxy = require('express-http-proxy');
const cors = require('cors');

const app = express();

app.use(cors({
  origin: 'http://localhost:4200',
  credentials: true
}));

// ✅ Proxy /api to your backend app (Tomcat, etc.)
app.use('/api', proxy('http://localhost:8080', {
  proxyReqPathResolver: req => `/eventvisualizer/app${req.url}`,

}));



const PORT = 3001;
app.listen(PORT, () => {
  console.log(`🚀 Unified proxy server running at http://localhost:${PORT}`);
});
