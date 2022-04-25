// @ts-check
// Note: type annotations allow type checking and IDEs autocompletion

const lightCodeTheme = require("prism-react-renderer/themes/github");
const darkCodeTheme = require("prism-react-renderer/themes/dracula");

/** @type {import('@docusaurus/types').Config} */
const config = {
  title: "onboarden docs",
  noIndex: false,
  url: "https://onboarden.io",
  baseUrl: "/",
  onBrokenLinks: "warn",
  onBrokenMarkdownLinks: "warn",
  favicon: "img/favicon.ico",
  organizationName: "ghostincjp", // Usually your GitHub org/user name.
  projectName: "dazaifu", // Usually your repo name.
  i18n: {
    defaultLocale: "ja",
    locales: ["ja"],
    // MEMO: 英語対応したらenを追加
    // locales: ["ja", "en"],
  },
  themeConfig:
    /** @type {import('@docusaurus/preset-classic').ThemeConfig} */
    ({
      // 検索を有効にする場合はコメントイン
      // TODO: algoliaの申請完了したらコメントアウトを外す
      // algolia: {
      //   // The application ID provided by Algolia
      //   appId: "YOUR_APP_ID",

      //   // Public API key: it is safe to commit it
      //   apiKey: "YOUR_SEARCH_API_KEY",

      //   indexName: "YOUR_INDEX_NAME",

      //   // Optional: see doc section below
      //   contextualSearch: true,

      //   // Optional: Specify domains where the navigation should occur through window.location instead on history.push. Useful when our Algolia config crawls multiple documentation sites and we want to navigate with window.location.href to them.
      //   externalUrlRegex: "external\\.com|domain\\.com",

      //   // Optional: Algolia search parameters
      //   searchParameters: {},

      //   // Optional: path for search page that enabled by default (`false` to disable it)
      //   searchPagePath: "search",
      // },
      // <meta name="robots" content="noindex">
      navbar: {
        hideOnScroll: true,
        title: "onboarden docs",
        items: [
          {
            type: "doc",
            docId: "overview",
            position: "left",
            label: "onboarden docs",
          },
          // 言語選択は有効にする場合はコメントインすること
          // {
          //   type: "localeDropdown",
          //   position: "right",
          // },
          // {
          //   type: 'separator',
          //   position: 'right',
          // },
        ],
      },
      prism: {
        theme: lightCodeTheme,
        darkTheme: darkCodeTheme,
      },
    }),
  plugins: [
    "docusaurus-plugin-sass",
    "@docusaurus/theme-live-codeblock",
    async function myPlugin(context, options) {
      return {
        name: "docusaurus-tailwindcss",
        configurePostCss(postcssOptions) {
          // Appends TailwindCSS and AutoPrefixer.
          postcssOptions.plugins.push(require("tailwindcss"));
          postcssOptions.plugins.push(require("autoprefixer"));
          return postcssOptions;
        },
      };
    },
  ],
  presets: [
    [
      "classic",
      /** @type {import('@docusaurus/preset-classic').Options} */
      ({
        docs: {
          routeBasePath: "/",
          sidebarPath: require.resolve("./sidebars.js"),
        },
        blog: false,
        theme: {
          customCss: [require.resolve("./styles/custom.scss")],
        },
      }),
    ],
  ],
};

module.exports = config;
