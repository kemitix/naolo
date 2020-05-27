import React from "react";
import NavItem from "./NavItem";

export interface NavigationItem {
    description: string;
    disabledIcon: string;
    enabled: boolean;
    enabledIcon: string;
    name: string;
    slug: string;
    weight: number;
}

export interface Feature {
    description: string;
    name: string;
    slug: string;
    weight: number;
    navigationItems: Array<NavigationItem>;
}

export interface NavigationProps {
    features: Array<Feature>;
    feature: string;
    serverUri: string;
}

const Navigation = (props: NavigationProps) => {
    return (
        <nav className="col-md-2 d-none d-md-block bg-light sidebar">
            <div className="sidebar-sticky">
                <ul className="nav flex-column">
                    {props.features.map(feature =>
                        feature.navigationItems.map(item => {
                            const itemUri = "/" + [feature.slug, item.slug].join('/');
                            return (
                                <NavItem key={itemUri}
                                         item={item}
                                         itemUri={itemUri}
                                         serverUri={props.serverUri}
                                />
                            );
                        })
                    )}
                </ul>
            </div>
        </nav>
    );
};

export default Navigation;
