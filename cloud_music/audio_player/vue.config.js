module.exports = {
    devServer: {
        proxy: {
            '/api': {
                target: 'http://120.79.162.149:3000',
                // ws: true,
                changeOrigin: true, //是否跨域
                pathRewrite: {
                    '^/api': '/' //路径重写 
                }
            },

        }
    },
    publicPath: './'
}