// src/app/app.components.ts

import { Component } from '@angular/core';
import { launch } from '@onboarden/importer';

const TEMPLATE_ID = 'YOUR_TEMPLATE_ID';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'my-app';

  async launchImporter() {
    launch({
      templateId: TEMPLATE_ID,
      onCompleted: (result) => console.log(result),
    });
  }
}

<!-- src/app/app.component.html -->

<button (click)="launchImporter()">Import</button>
