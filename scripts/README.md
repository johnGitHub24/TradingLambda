# scripts/ — Pure Surface

> Norm: `eos-minimal/knowledge/pure-project-scripts.md`  
> Apply: `eos-minimal/hooks/apply-workspace.ps1`

| File | Role |
|------|------|
| `portable-env.*` / `env.*` | OS `JAVA_HOME` |
| `check.*` | `gradlew check` |
| `intellij-run.properties` | no bootRun（本專案無 Spring Boot 應用） |
| `fix-intellij-run.ps1` | Local IDE helper（只說明 Gradle check；不產生 bootRun） |

Demo／docs 工具**不要**放這裡（FinTechDemo 用 `demo/`、`docs/tools/`）。
