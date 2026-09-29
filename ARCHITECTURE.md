# Architecture

## Layers

- `presentation`: Compose screens, navigation, theme, and ViewModels.
- `domain`: platform-independent models, repository contracts, and use cases.
- `data`: Room/DataStore implementations to be added in PHASE 3.
- `di`: Hilt modules and dependency wiring.
- `platform`: Android Telecom, speech, notification, and service adapters. Platform adapters must expose explicit capability/unsupported states.

## Rules

1. Presentation depends on domain contracts, never on Room or a concrete AI SDK.
2. Domain contains no Android framework types.
3. AI is represented by an `AIProvider` contract in its own boundary before any provider is selected.
4. Sensitive actions require explicit user confirmation.
5. Telecom behavior must be capability-driven; unsupported devices return a documented result, not a fake success.

## Initial package layout

```text
com.aiansweringmachine
├── data
├── di
├── domain
│   ├── model
│   ├── repository
│   └── usecase
├── platform
└── presentation
    ├── navigation
    ├── screen
    └── theme
```
