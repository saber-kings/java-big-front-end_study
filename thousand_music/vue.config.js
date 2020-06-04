const path = require("path");
module.exports = {
    lintOnSave:false,
    devServer: {
        proxy: {
            // localhost:8080
            '/v1': {
                target: 'https://musicapi.taihe.com',
                changeOrigin: true
            },
        }
    }
};