# dot-checkbox

<!-- Auto Generated Below -->


## Properties

| Property          | Attribute          | Description                                                                       | Type      | Default                    |
| ----------------- | ------------------ | --------------------------------------------------------------------------------- | --------- | -------------------------- |
| `disabled`        | `disabled`         | (optional) Disables field's interaction                                           | `boolean` | `false`                    |
| `hint`            | `hint`             | (optional) Hint text that suggest a clue of the field                             | `string`  | `''`                       |
| `label`           | `label`            | (optional) Text to be rendered next to input field                                | `string`  | `''`                       |
| `name`            | `name`             | Name that will be used as ID                                                      | `string`  | `''`                       |
| `options`         | `options`          | Value/Label checkbox options separated by comma, to be formatted as: Value\|Label | `string`  | `''`                       |
| `required`        | `required`         | (optional) Determine if it is mandatory                                           | `boolean` | `false`                    |
| `requiredMessage` | `required-message` | (optional) Text that will be shown when required is set and condition is not met  | `string`  | ``This field is required`` |
| `value`           | `value`            | Value set from the checkbox option                                                | `string`  | `''`                       |


## Events

| Event          | Description | Type                               |
| -------------- | ----------- | ---------------------------------- |
| `statusChange` |             | `CustomEvent<DotFieldStatusEvent>` |
| `valueChange`  |             | `CustomEvent<DotFieldValueEvent>`  |


## Methods

### `reset() => Promise<void>`

Reset properties of the field, clear value and emit events.

#### Returns

Type: `Promise<void>`




## Dependencies

### Depends on

- [dot-label](../dot-label)

### Graph
```mermaid
graph TD;
  dot-checkbox --> dot-label
  style dot-checkbox fill:#f9f,stroke:#333,stroke-width:4px
```

----------------------------------------------

*Built with [StencilJS](https://stenciljs.com/)*
