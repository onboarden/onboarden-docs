// src/App.tsx

import { launch } from '@onboarden/importer';
import React, { useEffect, useState } from 'react';

const TEMPLATE_ID = 'YOUR TEMPLATE ID';

function App() {
  const [token, setToken] = useState<string | undefined>();

  useEffect(() => {
    getToken();
  }, []);

  const getToken = async () => {
    const response = await fetch('http://localhost:8000');
    const { token } = await response.json();
    setToken(token);
  };

  const launchImporter = () => {
    launch({
      templateId: TEMPLATE_ID,
      jwt: token,
      onCompleted: (result) => console.log(result),
    });
  };

  return (
    <div>
      <button onClick={launchImporter} disabled={token === undefined}>
        Import
      </button>
    </div>
  );
}

export default App;
