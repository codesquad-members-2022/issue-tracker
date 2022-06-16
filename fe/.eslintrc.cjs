module.exports = {
  env: {
    browser: true,
    commonjs: true,
    es6: true,
    node: true,
  },
  settings: {
    "import/parsers": { "@typescript-eslint/parser": [".ts", ".tsx"] },
    "import/resolver": {
      node: {
        extensions: [".js", ".jsx", ".ts", ".tsx"],
      },
      typescript: {
        alwaysTryTypes: true,
      },
    },
    react: {
      version: "detect",
    },
  },
  parser: "@typescript-eslint/parser",
  parserOptions: {
    sourceType: "module",
    project: "./tsconfig.json",
    tsconfigRootDir: __dirname,
  },
  plugins: ["react-hooks", "react", "@typescript-eslint", "import", "prettier"],
  extends: [
    "eslint:recommended",
    "plugin:react/recommended",
    "plugin:@typescript-eslint/recommended",
    "plugin:import/typescript",
    "plugin:import/recommended",
    "plugin:prettier/recommended",
  ],
  rules: {
    "react-hooks/rules-of-hooks": "error",
    "react-hooks/exhaustive-deps": "warn",
    "react/jsx-uses-react": "off",
    "react/react-in-jsx-scope": "off",
    "@typescript-eslint/no-var-requires": 0,
    "import/no-unresolved": "off",
    "prettier/prettier": [
      "error",
      {
        endOfLine: "auto",
      },
    ],
    // https://github.com/import-js/eslint-plugin-import/blob/main/docs/rules/order.md 참조
    "import/order": [
      "error",
      {
        groups: ["builtin", "external", "internal", ["parent", "sibling"], "index", "object", "type"],
        pathGroups: [
          {
            pattern: "react",
            group: "builtin",
            position: "before",
          },
          {
            pattern: "@*/**",
            group: "internal",
            position: "after",
          },
        ],
        "newlines-between": "always",
        alphabetize: { order: "asc", caseInsensitive: true },
        warnOnUnassignedImports: true,
        pathGroupsExcludedImportTypes: [],
      },
    ],
  },
};
