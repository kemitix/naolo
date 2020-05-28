import React, {ChangeEvent, useState} from "react";
import {FieldsMeta} from "./ItemList";
import TextField from "./TextField";
import NumberField from "./NumberField";

interface AddItemProps {
    feature: string;
    meta: FieldsMeta
}

const AddItem = (props: AddItemProps) => {
    const [values, setValues] = useState({});

    const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
        setValues({
            ...values,
            [event.currentTarget.name]: event.currentTarget.value
        });
    }

    return (
        <form>
            <h2>{props.meta.name}</h2>
            <h3>Add {props.meta.name}</h3>
            {props.meta.fields
                .sort(((a, b) => a.order - b.order))
                .map(field => {
                //TODO: handle when field should be field.hidden
                switch (field.type) {
                    case "text":
                        return <TextField key={field.name}
                                          meta={field}
                                          onChange={handleChange} />;
                    case "number":
                        return <NumberField key={field.name}
                                            meta={field}
                                            onChange={handleChange} />;
                    // case "date":
                    //     //TODO: create a custom DateField
                    //     return <TextField meta={field} onChange={handleChange} />;
                    // case "datetime":
                    //     //TODO: create a custom DateTimeField
                    //     return <TextField meta={field} onChange={handleChange} />;
                    // case "enumeration":
                    //     //TODO: create a custom EnumerationField
                    //     return <TextField meta={field} onChange={handleChange} />;
                    // case "key":
                    //     //TODO: create a custom KeyField
                    //     return <TextField meta={field} onChange={handleChange} />;
                    default: // unsupported
                        return <div>
                            {field.label}: Unknown Field Type "{field.type}"
                        </div>
                }
            })}
            <input type="submit" value="Save" />
        </form>
    );
}

export default AddItem;
