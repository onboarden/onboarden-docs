// src/App.tsx

import { launch } from "@onboarden/importer";
import React from "react";

const TEMPLATE_ID = "YOUR TEMPLATE ID";

function App() {
  const launchImporter = () => {
    launch({ templateId: TEMPLATE_ID });
  };

  return (
    <div>
      <button onClick={launchImporter}>Import</button>
    </div>
  );
}

export default App;
