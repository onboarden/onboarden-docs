// src/App.tsx

import { launch } from "@onboarden/importer";
import React from "react";

const TEMPLATE_ID = "YOUR_TEMPLATE_ID";

function App() {
  const launchImporter = async () => {
    launch({
      templateId: TEMPLATE_ID,
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
