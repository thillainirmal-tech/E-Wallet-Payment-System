# Contributing

Open an issue before a substantial change. Create a focused branch from `main`, keep credentials and generated output out of commits, and validate both applications before opening a pull request:

```text
mvn --batch-mode -f ewallet/pom.xml verify
npm ci
npm run build
```

Include tests and documentation for changed behaviour. Report vulnerabilities privately as described in [SECURITY.md](SECURITY.md).
