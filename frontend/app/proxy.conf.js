const PROXY_CONFIG = [
    {
        context: [
            "/api"
        ],
        target:"https://shopwisely-api.herokuapp.com",
        secure: false,
        "changeOrigin": true
    }
]

module.exports = PROXY_CONFIG;