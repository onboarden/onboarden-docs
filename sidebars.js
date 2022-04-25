/**
 * Creating a sidebar enables you to:
 - create an ordered group of docs
 - render a sidebar for each doc of that group
 - provide next/previous navigation

 The sidebars can be generated from the filesystem, or explicitly defined here.

 Create as many sidebars as you want.
 */

// @ts-check

/** @type {import('@docusaurus/plugin-content-docs').SidebarsConfig} */
const sidebars = {
  tutorialSidebar: [
    "overview",
    {
      type: "category",
      collapsed: false,
      label: "Getting Started",
      items: [
        "getting-started/make-a-template",
        "getting-started/embedding",
        "getting-started/test-import",
        "getting-started/check-import",
      ],
      link: {
        type: "generated-index",
        title: "Getting Started",
        description: "onboardenのインポーターの使い方を知るためのガイドです。",
        slug: "/category/getting-started",
      },
    },
  ],
};

module.exports = sidebars;
