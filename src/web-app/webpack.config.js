const path = require("path");
const HtmlWebpackPlugin = require("html-webpack-plugin");
const CopyPlugin = require("copy-webpack-plugin");

module.exports = {
    entry: './src/app.js',
    output: {
      path: path.resolve(__dirname, 'dist'),
      filename: 'app.js',
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
        new HtmlWebpackPlugin({
            template: "./src/index.php",
            filename: "index.php"
        }),
        new CopyPlugin({
            patterns: [
                { from: "../../public", to: "public"}
            ],
        }),
      ]
};