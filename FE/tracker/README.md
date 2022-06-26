ref

# 베낀 코드

## + How to implement login with github in a react app

: https://levelup.gitconnected.com/how-to-implement-login-with-github-in-a-react-app-bd3d704c64fc

(아티클 내 참고사항)Login to your Github account and create an OAuth app by following the steps provided here.
: https://docs.github.com/en/developers/apps/building-oauth-apps/creating-an-oauth-app

**중요 포인트**

1. We imported and utilized AuthContext to make the global state and actions in our store available in this component.
2. When the user clicks the “Login with Github” button, a request is made to the Github API to authorize our app. If this goes successfully, Github redirects back to our app (Authorization callback URL) with a “code” in the url.
3. We utilize the useEffect hook to listen for when this “code” is available. Then we collect it from the url, use the code and other data such as: client_id, redirect_uri, client_secret to continue making requests to Github APIs through our proxy server (A simple express app to help us by-pass CORS error). I’ll talk more about the proxy server in the next step.
4. If the authentication through the proxy server returns a valid response, we dispatch the “LOGIN” event to set the user data and isLoggedIn payload in our store.

## + Github OAuth authentication react and node

: https://codevoweb.com/github-oauth-authentication-react-and-node/

## + Node TypeScript Mongodb JWT Authentication

: https://codevoweb.com/node-typescript-mongodb-jwt-authentication/

<br>
<br>

# 개념 참고

## + What is "User authorization callback URL" for?

: https://github.community/t/what-is-user-authorization-callback-url-for/13990

## + Basics of authentication(깃헙 공식문서)

: https://docs.github.com/en/rest/guides/basics-of-authentication#providing-a-callback

**Authorization callback URL** : This is easily the most important piece to setting up your application. It's the callback URL that GitHub returns the user to after successful authentication.

<br>
<br>

# 고마워요 에러 해결사!

## + Mathed leaf route at location "/" does not have an element

: https://stackoverflow.com/questions/69854011/matched-leaf-route-at-location-does-not-have-an-element

## + React: 'Redirect' is not exported from 'react-router-dom'

: https://stackoverflow.com/questions/63690695/react-redirect-is-not-exported-from-react-router-dom
