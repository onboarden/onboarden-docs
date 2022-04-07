<!--  src/App.vue -->

<script lang="ts">
import { launch } from "@onboarden/importer";

const TEMPLATE_ID = "YOUR_TEMPLATE_ID";

export default {
  methods: {
    open: async () => {
      const response = await fetch("http://localhost:8000");
      const { token } = await response.json();

      launch({
        templateId: TEMPLATE_ID,
        jwt: token,
        onCompleted: (result) => console.log(result),
      });
    },
  },
};
</script>

<template>
  <main>
    <button @click="open">Import</button>
  </main>
</template>
