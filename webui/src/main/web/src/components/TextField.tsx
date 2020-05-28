import React, {ChangeEvent} from "react";
import {Field} from "./ItemList";

interface TextFieldProps {
    meta: Field;
    onChange: (event: ChangeEvent<HTMLInputElement>) => void
}

const TextField = (props: TextFieldProps) => {
    return (
        <div className="form-group">
            <label htmlFor={props.meta.name}>{props.meta.label}</label>
            <input className="form-control"
                   type="text"
                   name={props.meta.name}
                   id={props.meta.name}
                   onChange={props.onChange}
                   value={props.meta.defaultValue}/>
            <br/>
        </div>
    );
}

export default TextField;
