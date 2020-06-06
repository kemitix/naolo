import React, {ChangeEvent, FormEvent, useState} from "react";
import {FieldsMeta} from "./ItemList";
import TextField from "./TextField";
import NumberField from "./NumberField";
import EnumerationField from "./EnumerationField";
import FieldValues from "../FieldValues";

interface AddItemProps {
    onSubmit: (event: FormEvent<HTMLFormElement>) => void;
    feature: string;
    meta: FieldsMeta;
    initialValues: FieldValues;
}

const AddItem = (props: AddItemProps) => {

    const [values, setValues] = useState(props.initialValues);

    const updateFieldValue = (name: string, value: string) =>
        setValues(values.with(name, value));

    const handleInputChange = (event: ChangeEvent<HTMLInputElement>) =>
        updateFieldValue(event.currentTarget.name, event.currentTarget.value)

    const handleSelectChange = (event: ChangeEvent<HTMLSelectElement>) =>
        updateFieldValue(event.currentTarget.name, event.currentTarget.value)

    return (
        <form onSubmit={props.onSubmit}>
            <h2>{props.meta.name}</h2>
            <h3>Add {props.meta.name}</h3>
            {props.meta.fields
                .sort(((a, b) => a.order - b.order))
                .filter(a => !a.generated)
                .map(field => {
                    //TODO: handle when field should be field.hidden
                    switch (field.type) {
                        case "text":
                            return <TextField
                                key={field.name}
                                meta={field}
                                onChange={handleInputChange} />;
                        case "number":
                            return <NumberField
                                key={field.name}
                                meta={field}
                                onChange={handleInputChange} />;
                        // case "date":
                        //     //TODO: create a custom DateField
                        //     return <TextField meta={field} onChange={handleChange} />;
                        // case "datetime":
                        //     //TODO: create a custom DateTimeField
                        //     return <TextField meta={field} onChange={handleChange} />;
                        case "enumeration":
                            let v = values.hasOwnProperty(field.name)
                                ? values.get(field.name).value
                                : "(none)";
                            return <EnumerationField
                                key={field.name}
                                meta={field}
                                options={props.meta.fields
                                    .filter(f => f.type === field.type)
                                    .flatMap(f => f.values)}
                                value={v}
                                onChange={handleSelectChange} />;
                        // case "key":
                        //     //TODO: create a custom KeyField
                        //     return <TextField meta={field} onChange={handleChange} />;
                        default: // unsupported
                            return <div key={field.name}>
                                {field.label}: Unknown Field Type "{field.type}"
                            </div>
                    }
                })}
            <input type="submit" value="Save" />
        </form>
    );
}

export default AddItem;
