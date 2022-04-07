<!--  src/App.vue -->

<script lang="ts">
import { launch } from "@onboarden/importer";

const TEMPLATE_ID = "YOUR_TEMPLATE_ID";

export default {
  methods: {
    open: async () => {
      launch({
        templateId: TEMPLATE_ID,
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
