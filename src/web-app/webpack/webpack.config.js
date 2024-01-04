const path = require("path");
const CopyPlugin = require("copy-webpack-plugin");
const CssMinimizerPlugin = require("css-minimizer-webpack-plugin");
const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const MiniCssExtractCleanupPlugin = require("./plugins/miniCssExtractCleanupPlugin");

module.exports = {
    entry: {
        "style": "./src/styles/style.scss",
        "dashboard": "./src/styles/pages/dashboard.scss",
        "error": "./src/styles/pages/error.scss",
        "forgot-password": "./src/styles/pages/forgot-password.scss",
        "sign-in": "./src/styles/pages/sign-in.scss",
        "sign-up": "./src/styles/pages/sign-up.scss",
    },
    output: {
        path: path.resolve(__dirname, "../dist"),
        filename: "[name].js",
    },
    stats: {
        errorDetails: true,
    },
    resolve: {
        alias: {
            "@Styles": path.resolve(__dirname, "../src/styles"),
            "@Public": path.resolve(__dirname, "../../../public"),
        },
    },
    module: {
        rules: [
            {
                test: /\.s?css$/,
                use: [
                    MiniCssExtractPlugin.loader,
                    {
                        loader: "css-loader",
                        options: {
                            url: false,
                        },
                    },
                    "sass-loader",
                ],
            },
        ],
    },
    optimization: {
        minimizer: [new CssMinimizerPlugin()],
    },
    plugins: [
        new MiniCssExtractPlugin({
            filename: "css/[name].css",
        }),
        new MiniCssExtractCleanupPlugin(["style.js", "dashboard.js", "error.js", "forgot-password.js", "sign-in.js", "sign-up.js"]),
        new CopyPlugin({
            patterns: [{ from: "src/js", to: "js" }],
        }),
        new CopyPlugin({
            patterns: [{ from: "src/index.php", to: "index.php" }],
        }),
        new CopyPlugin({
            patterns: [{ from: "src/config", to: "config" }],
        }),
        new CopyPlugin({
            patterns: [{ from: "src/templates", to: "templates" }],
        }),
        new CopyPlugin({
            patterns: [{ from: "src/controllers", to: "controllers" }],
        }),
        new CopyPlugin({
            patterns: [{ from: "src/models", to: "models" }],
        }),
        new CopyPlugin({
            patterns: [{ from: "src/views", to : "views"}],
        }),
        new CopyPlugin({
            patterns: [{ from: "src/exceptions", to : "exceptions"}],
        }),
        new CopyPlugin({
            patterns: [{ from: "../../public", to: "public" }],
        }),
        new CopyPlugin({
            patterns: [{ from: "src/components", to: "components" }],
        }),
        new CopyPlugin({
            patterns: [{ from: "src/.htaccess" }],
        }),
    ],
    devtool: "source-map",
};
