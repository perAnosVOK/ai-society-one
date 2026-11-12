# AI Society One — AI development coordinator (phase 1)

## Existing infrastructure
The separate `ai-society` Cloudflare Worker already invokes Google Gemini and Groq for independent Telegram answers. It stores conversations in D1. Its agent wake events are not equivalent to a GitHub coding agent. Do not replace or modify that production Worker as part of this phase.

## Provider policy
- Enabled for future coordinator: Gemini (`GEMINI_API_KEY`) and Groq (`GROQ_API_KEY`).
- OpenAI API: explicitly reserved; **never call** even if `OPENAI_API_KEY` is present.
- Claude, Grok (xAI), DeepSeek: not configured or verified.
- Groq is an inference provider, not Grok from xAI.

## Proposed safe workflow
1. An authenticated human submits a task with a repository path allowlist and acceptance criteria.
2. Coordinator creates a D1 task record: `queued -> running -> review -> approved/rejected`.
3. Gemini and Groq independently propose patches as text. Responses are untrusted.
4. Validate path allowlist, patch size, base commit SHA, and forbid secrets/workflow edits unless explicitly authorized.
5. Run static checks and Android CI on an isolated branch; never push directly to `main`.
6. Human approves the pull request before merge.
7. Store provider, model, task ID, status, error and cost/usage metadata for auditing.

## Integration requirements
- A separate Cloudflare Worker (suggested: `ai-society-one-coordinator`) with its own auth and narrowly scoped GitHub credential.
- D1 migrations for task state and audit events.
- Secure server-side provider calls, timeout/retry/budget limits.
- A supported ChatGPT tool/connector to invoke the coordinator. The presence of an HTTP endpoint alone does not grant ChatGPT permission to call it.
- Do not expose secrets or privileged operations via public GET routes.

## APK installation troubleshooting
The GitHub workflow now verifies that the debug APK is nonempty, has package ID `org.aisociety.one`, declares min SDK 26, and has a valid APK signature. This does not guarantee installation on every device. If installation fails, capture the exact Android installer message or `adb install -r app-debug.apk` output. A previously installed app signed by another certificate may require uninstalling the old app (after backing up its data).
