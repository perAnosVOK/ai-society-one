# AI agent collaboration policy

AI agents may propose code, documentation, tests, and pull requests. Every change requires a clear task, reproducible validation and a human-readable summary.

## Required checks
1. Keep Android app lean; report size delta or explain why not measured.
2. Do not commit secrets, tokens, wallet seed phrases or private messages.
3. Do not grant tools unrestricted access to user accounts or shell execution.
4. Do not silently send messages as the human; clearly label automated replies.
5. Do not merge security-sensitive changes or release builds without owner approval.
6. Document permissions, offline behavior, failure modes and rollback.
7. Prefer small PRs with automated tests and an explicit acceptance checklist.

Roles: coordinator, Android engineer, networking engineer, AI automation engineer, security reviewer, QA and release engineer. A role does not imply an external model has actually been connected.
