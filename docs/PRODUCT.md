# AI Society One — Product requirements (v0.1)

## Vision
A compact, open-source Android communication app combining its own messenger, social feed, internal mailbox, browser, and lightweight rule-based AI representative. A wallet and separate AI core are future optional modules.

## Non-negotiable principles
- Minimize APK download size, installed footprint, memory, battery, and network use; measure rather than promise fixed sizes.
- No bundled large language model in v0.1. Offline rules and templates work without connectivity.
- Private messages are end-to-end encrypted in the future network implementation; never claim encryption until independently tested.
- AI auto-replies must be visibly labeled as automated and operate within user-defined permissions and time limits.
- No autonomous financial transfers, account deletion, or sensitive disclosures.
- Cache is bounded; never silently delete user content, private keys, or durable memory.
- Wallet design is optional, isolated, and disabled until security review; no token issuance in v0.1.
- No centralized plaintext message archive. Offline delivery requires a defined encrypted relay/mailbox strategy.

## Roadmap
1. Buildable Android skeleton, navigation, offline scripted assistant, cache policy, CI size reporting.
2. Local message store and user profiles, draft conversations, auto-reply policies and tests.
3. Secure network identity, encrypted one-to-one messaging, offline delivery.
4. Social feed and internal mail, WebView browser with safe defaults.
5. Wallet interface behind a disabled feature flag; threat model and testnet prototype.
6. External AI core protocol, opt-in cloud intelligence and verified knowledge exchange.

## Release criteria
Each PR states APK size delta, privacy impact, battery/network impact, tests and rollback plan.
