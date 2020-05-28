import React, {ChangeEvent} from "react";
import {Field} from "./ItemList";

interface NumberFieldProps {
    meta: Field;
    onChange: (event: ChangeEvent<HTMLInputElement>) => void;
}

const NumberField = (props: NumberFieldProps) => {
    console.log(props.meta);
    return (
        <div className="form-group">
            <label htmlFor={props.meta.name}>{props.meta.label}</label>
            <input className="form-control"
                   type="number"
                   name={props.meta.name}
                   min={props.meta.minMax === undefined ? undefined : props.meta.minMax.min}
                   max={props.meta.minMax === undefined ? undefined : props.meta.minMax.max}
                   id={props.meta.name}
                   onChange={props.onChange}
                   value={props.meta.defaultValue}/>
        </div>
    );
}

export default NumberField;