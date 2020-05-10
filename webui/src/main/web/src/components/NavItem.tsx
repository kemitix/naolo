import React from "react";
import {NavigationItem} from "./Navigation";
import {Link} from "react-router-dom";

interface NavItemProps {
    item: NavigationItem,
    itemUri: string,
    serverUri: string
}

const NavItem = (props: NavItemProps) => {

    const navItemStyle = "f4 fw7 dib pa2 no-underline";
    const enabledStyle = "bg-animate bg-white hover-bg-light-blue black";
    const disabledStyle = "bg-near-white gray";

    function resourceUri(path: string) {
        return [props.serverUri, path].join("/");
    }

    const enabledItem = (
        <Link to={props.itemUri}
              className={enabledStyle + " " + navItemStyle}>
            <img src={resourceUri(props.item.enabledIcon)}
                 alt=""/>
            {props.item.name}
        </Link>
    );
    const disabledItem = (
        <span className={disabledStyle + " " + navItemStyle}>
            <img src={resourceUri(props.item.disabledIcon)}
                 alt=""/>
            {props.item.name}
        </span>
    );
    return (
        <div className="pa4 lh-copy">
            {props.item.enabled
                ? enabledItem
                : disabledItem}
        </div>
    );
};

export default NavItem;
