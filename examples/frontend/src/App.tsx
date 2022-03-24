import { launch } from "@onboarden/importer";
import React, { useEffect, useState } from "react";

const TEMPLATE_ID = "YOUR TEMPLATE ID"; // change this

function App() {
  const [token, setToken] = useState<string | undefined>();

  useEffect(() => {
    getToken();
  }, []);

  const getToken = async () => {
    const response = await fetch("http://localhost:8000");
    const { token } = await response.json();
    setToken(token);
  };

  const launchImporter = () => {
    launch({ templateId: TEMPLATE_ID, jwt: token });
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
