import React, { useEffect } from 'react';
import axios from 'axios';

function App() {
  useEffect(() => {
    (async () => {
      const data = await axios.get(
        'https://github.com/login/oauth/authorize?client_id=6996df6e85cf747c9098&scope=repo:status read:repo_hook user:email&redirect_uri=http://localhost:3000',
      );
      console.log(data);
    })();
  }, []);

  return <div />;
}

export default App;
