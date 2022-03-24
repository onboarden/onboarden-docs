# dazaifu docs

dazaifu の顧客向けドキュメント

## Hosting

- Netlify を使っています
- ドメインの設定は Netlify を見てください

## Deploy

- `main` ブランチにマージしたらデプロイされます

## Dependency

- ドキュメント生成に Docusaurus を使っています
- https://docusaurus.io

## ドキュメントの書き方

- `docs`以下に作っていきます
- ディレクトリ構成とルーティングは対応しています
- Markdown の書き方などは Docusaurus のドキュメントを参照
- 参考: https://docusaurus.io/docs/markdown-features
- MDX も書けます

### ミニマムな書き方

- ↓ sidebar_position は同じ階層にいるファイル達の表示順です

```markdown
---
sidebar_position: 4
---

# Hello world

本文
```

## i18n

- メインが日本語で、英語にも対応させています。
- 日本語のドキュメントは `docs/` 以下、 英語は `i18n/en/docusaurus-plugin-content-docs/current/` 以下がそれぞれのルートディレクトリで、その中に同じディレクトリ構造とファイル名でファイルを作っていきます
- 英語版のページがない場合は、日本語のページがフォールバックされるようになっています

## デザインのカスタマイズ

- `styles/custom.scss` に書くと反映されます
