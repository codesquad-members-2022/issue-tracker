import theme from '@/style/theme';

const iconStyle = [
  {
    iconType: 'bottomArrow',
    iconVersion: 'black',
    modes: [
      {
        iconMode: 'initial',
        color: theme.colors.greyScale.label,
      },
      {
        iconMode: 'hover',
        color: theme.colors.greyScale.body,
      },
    ],
  },
  {
    iconType: 'label',
    iconVersion: 'black',
    modes: [
      {
        iconMode: 'initial',
        color: theme.colors.greyScale.label,
      },
      {
        iconMode: 'hover',
        color: theme.colors.greyScale.body,
      },
      {
        iconMode: 'active',
        color: theme.colors.greyScale.titleActive,
      },
    ],
  },
  {
    iconType: 'mileStone',
    iconVersion: 'black',
    modes: [
      {
        iconMode: 'initial',
        color: theme.colors.greyScale.label,
      },
      {
        iconMode: 'hover',
        color: theme.colors.greyScale.body,
      },
      {
        iconMode: 'active',
        color: theme.colors.greyScale.titleActive,
      },
    ],
  },
];

export default iconStyle;
