import React from "react";
import {NavigationItem} from "./Navigation";
import {Link} from "react-router-dom";

interface NavItemProps {
    item: NavigationItem,
    itemUri: string,
    serverUri: string
}

const NavItem = (props: NavItemProps) => {

    const navItemStyle = "nav-link";
    const enabledStyle = "active";
    const disabledStyle = "";

    function resourceUri(path: string) {
        return [props.serverUri, path].join("/");
    }

    const enabledItem = (
        <Link to={props.itemUri}
              className={enabledStyle + " " + navItemStyle}>
            <img src={resourceUri(props.item.enabledIcon)}
                 className="feather feather-home"
                 width="24"
                 alt=""/>
            {props.item.name}
        </Link>
    );
    const disabledItem = (
        <span className={disabledStyle + " " + navItemStyle}>
            <img src={resourceUri(props.item.disabledIcon)}
                 className="feather feather-home"
                 width="24"
                 alt=""/>
            {props.item.name}
        </span>
    );
    return (
        <li className="nav-item">
            {props.item.enabled
                ? enabledItem
                : disabledItem}
        </li>
    );
};

export default NavItem;
