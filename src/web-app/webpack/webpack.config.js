const path = require("path");
const CopyPlugin = require("copy-webpack-plugin");
const CssMinimizerPlugin = require("css-minimizer-webpack-plugin");
const TerserPlugin = require("terser-webpack-plugin");
const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const MiniCssExtractCleanupPlugin = require("./plugins/miniCssExtractCleanupPlugin");
const glob = require("glob");

// Regex used to extract the filename before the .scss extension
const SCSS_FILENAME_REGEX = /(([^\/]+)\.scss)$/

// Fetches all the pages's specific stylesheet and adds "./" to the path
// for webpack to use relative and not absolute pathin when resolving entrypoints
pages_stylesheets = glob.sync("./styles/pages/*.scss");
pages_stylesheets = pages_stylesheets.map(e => `./${e}`);

// Reduces the array to a map having for key the filename without the extension
// and the path as the value
pages_stylesheets = pages_stylesheets.reduce((a, v) => ({ ...a, [v.match(SCSS_FILENAME_REGEX)[2]]: v}), {}) 

// Compile target entrypoints
entrypoints = {
    style: "./styles/style.scss",
    ...pages_stylesheets
}

module.exports = {
    entry: entrypoints,
    output: {
        path: path.resolve(__dirname, "../dist"),
        filename: "[name].js",
    },
    stats: {
        errorDetails: true,
    },
    resolve: {
        alias: {
            "@Styles": path.resolve(__dirname, "../styles"),
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
        minimizer: [
            new CssMinimizerPlugin(),
            new TerserPlugin({
                test: /\.js(\?.*)?$/i,
            })
        ]
    },
    plugins: [
        new MiniCssExtractPlugin({
            filename: "css/[name].css",
        }),
        // Removes all js file associated with the scss entrypoints
        new MiniCssExtractCleanupPlugin(Object.keys(entrypoints).map(e => `${e}.js`)),
        new CopyPlugin({
            patterns: [{ from: "js", to: "js" }],
        }),
        new CopyPlugin({
            patterns: [{ from: "index.php", to: "index.php" }],
        }),
        new CopyPlugin({
            patterns: [{ from: "config", to: "config" }],
        }),
        new CopyPlugin({
            patterns: [{ from: "templates", to: "templates" }],
        }),
        new CopyPlugin({
            patterns: [{ from: "src", to: "src" }],
        }),
        new CopyPlugin({
            patterns: [{ from: "views", to : "views"}],
        }),
        new CopyPlugin({
            patterns: [{ from: "../../public", to: "public" }],
        }),
        new CopyPlugin({
            patterns: [{ from: "components", to: "components" }],
        }),
        new CopyPlugin({
            patterns: [{ from: ".htaccess" }],
        }),
        new CopyPlugin({
            patterns: [{ from: "vendor", to: "vendor" }],
        }),
    ],
    devtool: "source-map",
};