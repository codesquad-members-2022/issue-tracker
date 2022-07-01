const HtmlWebpackPlugin = require('html-webpack-plugin');
const dotenv = require('dotenv');
const path = require('path');
const webpack = require('webpack');

dotenv.config();
const WebpackEnvironmentPlugin = new webpack.EnvironmentPlugin(['BASE_URL']);

module.exports = {
  entry: `${path.resolve(__dirname, '../src')}/index.tsx`,
  module: {
    rules: [
      {
        test: /\.(ts|tsx|js|jsx)$/,
        use: 'babel-loader',
        exclude: /node_modules/
      },
      {
        test: /\.svg$/,
        use: ['@svgr/webpack']
      }
    ]
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: `${path.resolve(__dirname, '../public')}/index.html`
    }),
    new webpack.ProvidePlugin({
      React: 'react'
    }),
    WebpackEnvironmentPlugin
  ],
  resolve: {
    alias: {
      '@': path.resolve(__dirname, '../src')
    },
    extensions: ['.js', '.jsx', '.ts', '.tsx', '.css']
  }
};
