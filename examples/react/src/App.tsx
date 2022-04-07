// src/App.tsx

import { launch } from '@onboarden/importer';
import React from 'react';

const TEMPLATE_ID = 'YOUR_TEMPLATE_ID';

function App() {
  const launchImporter = async () => {
    const response = await fetch('http://localhost:8000');
    const { token } = await response.json();

    launch({
      templateId: TEMPLATE_ID,
      jwt: token,
      onCompleted: (result) => console.log(result),
    });
  };

  return (
    <div>
      <button onClick={launchImporter}>Import</button>
    </div>
  );
}

export default App;
