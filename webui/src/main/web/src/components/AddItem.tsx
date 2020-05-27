import React, {ChangeEvent, useState} from "react";
import {useParams} from "react-router-dom";
import {FieldsMeta} from "./ItemList";
import TextField from "./TextField";

interface AddItemProps {
    feature: string;
    meta: FieldsMeta
}

const AddItem = (props: AddItemProps) => {
    const {feature} = useParams();

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
            {props.meta.fields.map(field => {
                //TODO: handle sorting fields by field.order
                //TODO: handle when field should be field.hidden
                switch (field.type) {
                    case "text":
                        return <TextField meta={field} onChange={handleChange} />;
                    // case "number":
                    //     //TODO: create a custom NumberField
                    //     return <TextField meta={field} onChange={handleChange} />;
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
                        return <div>Unknown Field Type: {field.type}</div>
                }
            })}
            <input type="submit" value="Save" />
        </form>
    );
}

export default AddItem;
