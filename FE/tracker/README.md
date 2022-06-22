FRONTEND README.. reference..

# 베낀 코드

## [How to implement login with github in a react app](https://levelup.gitconnected.com/how-to-implement-login-with-github-in-a-react-app-bd3d704c64fc)

<br>

**중요 포인트**

    1. We imported and utilized AuthContext to make the global state and actions in our store available in this component.

    2. When the user clicks the “Login with Github” button, a request is made to the Github API to authorize our app. If this goes successfully, Github redirects back to our app (Authorization callback URL) with a “code” in the url.

    3. We utilize the useEffect hook to listen for when this “code” is available. Then we collect it from the url, use the code and other data such as: client_id, redirect_uri, client_secret to continue making requests to Github APIs through our proxy server (A simple express app to help us by-pass CORS error). I’ll talk more about the proxy server in the next step.

    4. If the authentication through the proxy server returns a valid response, we dispatch the “LOGIN” event to set the user data and isLoggedIn payload in our store.

[(아티클 내 reference link ^^ )Login to your Github account and create an OAuth app by following the steps provided here.](https://docs.github.com/en/developers/apps/building-oauth-apps/creating-an-oauth-app)

## [Github OAuth authentication react and node](https://codevoweb.com/github-oauth-authentication-react-and-node/)

## [Node TypeScript Mongodb JWT Authentication](https://codevoweb.com/node-typescript-mongodb-jwt-authentication/)

## [How to Create the Matrix Text Effect With JavaScript](https://betterprogramming.pub/how-to-create-the-matrix-text-effect-with-javascript-325c6bb7d96e)

## [MUI Table(with TypeScript)](https://mui.com/material-ui/react-table/)

<br>
<br>

# 개념 참고

## [What is "User authorization callback URL" for?](https://github.community/t/what-is-user-authorization-callback-url-for/13990)

## [Basics of authentication(깃헙 공식문서)](https://docs.github.com/en/rest/guides/basics-of-authentication#providing-a-callback)

    Authorization callback URL : This is easily the most important piece to setting up your application. It's the callback URL that GitHub returns the user to after successful authentication.

## [(캔버스 개념) Canvas With React JS](https://medium.com/@pdx.lucasm/canvas-with-react-js-32e133c05258)

```javascript
const canvasRef = useRef(null);
const canvas = canvasRef.current;
const context = canvas.getContext("2d");
```

    What? is it broken? Cannot read property getContext of null? getContext s not a fnction?

    The component is not mounted yet when we tried to get the canvas through the ref, so its value is, naturally, the initial value that we gave for it(which is null in my case). We must wait the componenet did mount properly before get the real canvas...

    The __useEffect__ hook allow us to perform side effects in function components... the component did amount. Right after the canvas element is available in the dom for us, we want to get it on javascript to take its context and make some draw... If we pass only the first argument(the function), useEffect will call the function after every single update of the componenet.

## [export VS export default](https://mattshelley.dev/export-vs-export-default/)

    If you're only exporting a single class or function, use export default... If a module's primary purpose is to house one specific export, then you should consider exporting it as a default export. This makes both importing and actually using the import a little easieer.

    Further, TypeScript recommends named exports when there are multiple things to export

## [Ways of Getting Data from API in React](https://dev.to/olenadrugalya/ways-of-getting-data-from-api-in-react-2kpf)

<br>
<br>

# 고마워요 에러 해결사!

## + Mathed leaf route at location "/" does not have an element

: https://stackoverflow.com/questions/69854011/matched-leaf-route-at-location-does-not-have-an-element

## + React: 'Redirect' is not exported from 'react-router-dom'

: https://stackoverflow.com/questions/63690695/react-redirect-is-not-exported-from-react-router-dom
