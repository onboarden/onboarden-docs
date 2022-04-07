// src/app/app.components.ts

import { Component } from '@angular/core';
import { launch } from '@onboarden/importer';

const TEMPLATE_ID = 'YOUR TEMPLATE ID';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'my-app';

  async launchImporter() {
    const response = await fetch('http://localhost:8000');
    const { token } = await response.json();

    launch({
      templateId: TEMPLATE_ID,
      jwt: token,
      onCompleted: (result) => console.log(result),
    });
  }
}
