# Architecture decision record 001 — compact Android-first

## Client
- Kotlin Android app, single activity and small feature modules only when modularization saves maintainability.
- Jetpack Compose considered for UI; compare release APK size against lean native views before committing.
- Room/SQLite for durable local state; Android WebView for browsing; Android Keystore for protecting locally generated secrets.
- WorkManager for deferrable sync/cleanup; no perpetual background service by default.
- AI representative v0.1 is a deterministic rules engine with intent patterns and user-editable templates, not a generative model.

## Boundaries
- Communication domain: identities, chats, internal mail, social feed.
- Assistant domain: local rules, permissions, availability modes, auto-reply audit trail.
- Network domain: pluggable transport; prototype can be local-only before real delivery.
- Browser domain: isolated WebView with explicit navigation and download controls.
- Wallet domain: separate opt-in feature; never mix signing keys with AI tools.
- Future AI core: versioned request/response interface; explicit consent before sharing private content externally.

## Security work before real network launch
Define identity recovery, key verification, E2EE protocol, spam/rate limits, metadata retention, NAT/relay behavior, abuse reporting and deletion semantics. Avoid inventing cryptography.

## Size measurement
Build a release variant in CI; record APK bytes and compare to baseline. Target 20–35 MB is an aspiration only, not a verified guarantee.
