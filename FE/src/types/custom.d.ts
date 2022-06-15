declare module '*.svg' {
  import React = require('react');

  export const ReactComponent: React.SFC<React.SVGProps<SVGElement>>;
  const src: string;
  export default src;
}
