import React from "react";
import NavItem from "./NavItem";

export interface NavigationItem {
    description: string,
    disabledIcon: string,
    enabled: boolean,
    enabledIcon: string,
    name: string,
    slug: string,
    weight: number
}

interface Feature {
    description: string,
    name: string,
    slug: string,
    weight: number,
    navigationItems: Array<NavigationItem>
}

export interface NavigationProps {
    features: Array<Feature>,
    apiUri: string,
    baseUri: string
}

const Navigation = (props: NavigationProps) => {
    return (
        <nav className="fl w-20 bg-near-white tc">
            <div>Navigation side bar</div>
            {props.features.map(feature =>
                feature.navigationItems.map(item => {
                    const slug = feature.slug + '/' + item.slug;
                    const uri = [props.baseUri, slug].join('/');
                    return (
                        <NavItem key={slug}
                                 item={item}
                                 uri={uri}
                        />
                    );
                })
            )}
        </nav>
    );
};

export default Navigation;
