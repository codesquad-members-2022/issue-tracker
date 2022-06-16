module.exports = {
  parser: '@typescript-eslint/parser',
  plugins: ['@typescript-eslint', 'prettier'],
  extends: [
    'airbnb',
    'plugin:import/errors',
    'plugin:import/warnings',
    'plugin:prettier/recommended',
    'plugin:@typescript-eslint/recommended',
    'prettier/@typescript-eslint',
  ],
  rules: {
    'linebreak-style': 'off',
    'import/prefer-default-export': 'off',
    'prettier/prettier': 'off', // prettier 규칙을 지키지 않았을 때, lint 에러로 만들 것인지
    'import/extensions': 'off', // import 할 때, 확장자명을 적을 것인지
    'import/no-unresolved': 'off', // 경로가 잘못됐을 때 에러 만들 것인지
    'import/no-extraneous-dependencies': 'off', // 테스트 또는 개발환경을 구성하는 파일에서는 devDependency 사용을 허용
    'react/jsx-filename-extension': [
      2,
      { extensions: ['.js', '.jsx', '.ts', '.tsx'] },
    ],
    'jsx-a11y/no-noninteractive-element-interactions': 'off',
  },
};
