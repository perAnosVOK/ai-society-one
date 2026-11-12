# File Box and Android updates — design v0.1

## File Box ("Мой ящик")
Users explicitly select files via Android Storage Access Framework (ACTION_OPEN_DOCUMENT); copy only selected content into an app-managed staging area, then upload to a remote object store. Never request MANAGE_EXTERNAL_STORAGE or broad filesystem access. Local originals remain untouched.

### Sharing
- Private by default; user must explicitly publish a share.
- Generate cryptographically random, unguessable share tokens; never use predictable IDs.
- Recipient can download only the shared object(s), never browse owner's phone or account.
- Optional expiration, download count, password and revocation; enforce all limits on server.
- Show uploaded size, quota, progress, and retention policy.
- Public-link recipients may need a compatible app; universal HTTPS link is preferable for interoperability, with explicit user choice about authentication.
- Encrypt files in transit. For private shares, prefer client-side encryption and out-of-band key sharing; define key recovery and metadata leakage before claiming end-to-end encryption.
- Malware scanning / abuse reporting / rate limits must be designed without falsely claiming server-side plaintext inspection of client-encrypted files.
- Do not claim unlimited free storage. Choose object storage provider after evaluating pricing, quota, deletion and privacy.
- No background access to other phone files.

### Data model
FileObject(id, ownerId, name, mimeType, byteSize, storageKey, createdAt, encryptionMetadata)
Share(id, ownerId, fileObjectIds, tokenHash, expiresAt?, maxDownloads?, downloads, revokedAt?)
Storage and share metadata must be scoped to authenticated owner. Download authorization must be checked server-side.

## Automatic app updates
- CI builds signed release APK using protected secrets, versionCode strictly increasing; never expose signing keys to AI agents or logs.
- Publish versioned release metadata: versionCode, versionName, APK URL, SHA-256, release notes, minimum supported version.
- App periodically checks a pinned HTTPS update manifest. Compare versions and verify release integrity/signing identity.
- On user-approved installation, hand off APK to Android's package installer using FileProvider and proper scoped URI grants. Android requires user confirmation for ordinary sideloaded app updates; do not promise silent install.
- No update from untrusted URLs; no dynamic downloading/executing arbitrary Android code.
- Consider Google Play in-app updates if Play distribution is selected later.
- Development/debug builds are not interchangeable with production-signed releases; establish a stable signing strategy before shipping updates.

## Phases
1. File Box UI + local picker + private local copy + delete.
2. Backend storage adapter + authenticated upload/download + quotas.
3. Share links, expiry, revocation, download limits and audit.
4. Encrypted sharing, threat modeling and recovery.
5. Signed releases and opt-in update notification/installation.

Status: design only, not yet implemented.
