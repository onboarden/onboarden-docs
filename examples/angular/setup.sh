npx -p @angular/cli@latest ng new my-app
npm install @onboarden/importer

# change angular.json
```
{
  ...
  "serve": {
    "options": {
      "port": 3000 // add this config for CORS
    },
    "builder": "@angular-devkit/build-angular:dev-server",
  ...
},
}
```