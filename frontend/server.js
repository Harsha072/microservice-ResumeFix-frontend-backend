const express = require('express');
const cors = require('cors');
const path = require('path');

const app = express();
const PORT = 5500;

// Enable CORS for all routes
app.use(cors());

// Serve static files from the "public" folder
app.use(express.static(path.join(__dirname, 'public')));
console.log(__dirname);

app.get('/', function(req, res){
    res.sendFile(__dirname + '/public/fileUpload.html');
    });

// Start the server
app.listen(PORT, () => {
  console.log(`Frontend server running at http://localhost:${PORT}`);
});