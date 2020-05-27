import React, {ChangeEvent} from "react";
import {Field} from "./ItemList";

interface TextFieldProps {
    meta: Field;
    onChange: (event: ChangeEvent<HTMLInputElement>) => void
}

const TextField = (props: TextFieldProps) => {
    return (
        <>
            <label htmlFor={props.meta.name}>{props.meta.label}</label>
            <input type="text" name={props.meta.name} id={props.meta.name}
                   onChange={props.onChange}
                   value={props.meta.defaultValue}/>
            <br/>
        </>
    );
}

export default TextField;
