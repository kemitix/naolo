import React from "react";

interface NavItemProps {
    label: string;
    enabled: boolean;
    uri: string
}

const NavItem = (props: NavItemProps) => {
    const enabledItem = (
        <a href={props.uri} className="f4 fw7 dib pa2 no-underline bg-animate bg-white hover-bg-light-blue black">
            {props.label}
        </a>);
    const disabledItem = (
        <span className="f4 fw7 dib pa2 no-underline bg-near-white gray">
            {props.label}
        </span>
    );
    return(
        <li className="pa4 lh-copy">
            {props.enabled ? enabledItem : disabledItem }
        </li>
    )};

export default NavItem;
