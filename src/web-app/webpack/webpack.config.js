const path = require("path");
const CopyPlugin = require("copy-webpack-plugin");
const CssMinimizerPlugin = require("css-minimizer-webpack-plugin");
const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const MiniCssExtractCleanupPlugin = require("./plugins/miniCssExtractCleanupPlugin");

module.exports = {
    entry: {
        style: "./src/styles/style.scss"
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
            filename: "css/styles.css",
        }),
        new MiniCssExtractCleanupPlugin(["style.js"]),
        new CopyPlugin({
            patterns: [{ from: "src/js", to: "js" }],
        }),
        new CopyPlugin({
            patterns: [{ from: "src/index.php", to: "index.php" }],
        }),
        new CopyPlugin({
            patterns: [{ from: "src/app-config.php", to: "app-config.php" }],
        }),
        new CopyPlugin({
            patterns: [{ from: "src/template.php", to: "template.php" }],
        }),
        new CopyPlugin({
            patterns: [{ from: "src/pages" }],
        }),
        new CopyPlugin({
            patterns: [{ from: "../../public", to: "public" }],
        }),
        new CopyPlugin({
            patterns: [{ from: "src/components", to: "components" }],
        }),
        new CopyPlugin({
            patterns: [{ from: "src/error-pages", to: "error-pages" }],
        }),
        new CopyPlugin({
            patterns: [{ from: "src/.htaccess" }],
        }),
    ],
    devtool: "source-map",
};
