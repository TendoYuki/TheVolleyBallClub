const path = require("path");
const HtmlWebpackPlugin = require("html-webpack-plugin");
const CopyPlugin = require("copy-webpack-plugin");

module.exports = {
    entry: {
      app: './src/app.js',
      "components/navbar/navbar": './src/components/navbar/navbar.js',
      "components/carrousel/carrousel": './src/components/carrousel/carrousel.js'
    },
    output: {
      path: path.resolve(__dirname, 'dist'),
      filename: '[name].js',
    },
  
    resolve: {
      alias: {
        "@Styles": path.resolve(__dirname, "src/styles"),
        "@Public": path.resolve(__dirname, "../../public")
      }
    },
    module: {
      rules: [
        {
          test: /\.s[ac]ss$/i,
          use: [
            // Creates `style` nodes from JS strings
            "style-loader",
            // Translates CSS into CommonJS
            "css-loader",
            // Compiles Sass to CSS
            "sass-loader",
          ],
        },
      ],
    },
    plugins: [
        new CopyPlugin({
            patterns: [
                { from: "src/index.php", to: "index.php"}
            ],
        }),
        new CopyPlugin({
            patterns: [
                { from: "src/app-config.php", to: "app-config.php"}
            ],
        }),
        new CopyPlugin({
            patterns: [
                { from: "src/pages", to: "pages"}
            ],
        }),
        new CopyPlugin({
            patterns: [
                { from: "../../public", to: "public"}
            ],
        }),
        new CopyPlugin({
            patterns: [
                { from: "src/components", to: "components"}
            ],
        }),
      ]
};